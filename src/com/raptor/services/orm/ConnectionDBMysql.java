package com.raptor.services.orm;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import com.raptor.entities.condition.Condition;
import com.raptor.entities.core.Article;
import com.raptor.entities.core.Scenario;
import com.raptor.entities.core.User;
import com.raptor.entities.tag.Tag;
import com.raptor.entities.task.Task;
import com.raptor.entities.task.TaskAction;
import com.raptor.entities.task.TaskActionTranslate;
import com.raptor.entities.task.TaskCrawl;
import com.raptor.entities.task.TaskCrawlHtml;
import com.raptor.entities.task.TaskSender;
import com.raptor.entities.task.TaskSenderBlog;
import com.raptor.entities.task.TaskSenderBlogWordpress;
import com.raptor.entities.task.TaskSenderEmail;
import com.raptor.factories.ConditionFactory;
import com.raptor.factories.TagFactory;
import com.raptor.factories.TaskFactory;
import com.raptor.properties.Constants;
import com.raptor.properties.Log;
import com.raptor.properties.RobotProperties;


/**
 * Class using to store datas in a mysql database
 * @author Eric Pidoux
 * @version 1.0
 *  This file is part of Raptor.
 *  Raptor is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Raptor is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Raptor.  If not, see <http://www.gnu.org/licenses/>
 *
 */
public class ConnectionDBMysql extends ConnectionDB{
	
		public static DateFormat DF = new SimpleDateFormat("yyyy-MM-d H:m:s");
		
	    private java.sql.Connection dbConnect = null;
	    
	    private java.sql.Statement dbStatement = null;

	    /**
	     * Constructor
	     * @throws IOException 
	     */
	    public ConnectionDBMysql() throws IOException {
	    	try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            Log.getInstance().info("Trying to connect to jdbc:mysql://" + RobotProperties.getInstance().getStorageDBHost()+"/"+RobotProperties.getInstance().getStorageDBName()+" with login :"+ RobotProperties.getInstance().getStorageDBLogin());
	            this.dbConnect = DriverManager.getConnection("jdbc:mysql://" + RobotProperties.getInstance().getStorageDBHost()+"/"+RobotProperties.getInstance().getStorageDBName(), RobotProperties.getInstance().getStorageDBLogin(), RobotProperties.getInstance().getStorageDBPass());
	            this.dbStatement = this.dbConnect.createStatement();
	        } catch (Exception ex) {
	            Log.getInstance().error("Error while attempting to connect to the database", ex);
	        } 
	    }

	    /**
	     * Execute a sql request for SELECT only
	     * @param sql the sql request from SELECT type
 	     * @return the resultset of the request
	     * @throws Exception 
	     */
	    private ResultSet exec(String sql) throws Exception {
	    	ResultSet rs = null;
	        try {
	        	this.dbStatement = this.dbConnect.createStatement();
	        	rs=dbStatement.executeQuery(sql);
	            
	        } catch (Exception ex) {
	            Log.getInstance().error("the SQL request ( "+sql+" ) cannot be executed", ex);
	            throw ex;
	        }
	        return rs;
	    }
	    
	    /**
	     * Execute a request SQL insert, delete or update
	     * @param sql the sql request from insert,delete or update type
	     * @return true if the request was executed, false otherwise
	     * @throws Exception 
	     */
	    @SuppressWarnings("unused")
		private Boolean execQuery(String sql) throws Exception {
	    	try {
	            return this.dbConnect.createStatement().execute(sql);
	   
	        } catch (Exception ex) {
	            Log.getInstance().error("the SQL request ( "+sql+" ) cannot be executed", ex);
	            throw ex;
	        }
	    }
	    
	    /**
	     * Execute a request SQL insert, delete or update preparedStatement
	     * @param sql the request
	     * @param params all params
	     * @return true if it was executed, false otherwise
	     * @throws Exception 
	     */
	    private Boolean execPreparedQuery(String sql,List<Object> params) throws Exception {
	    	try {
	    		PreparedStatement pstmt = (PreparedStatement) this.dbConnect.prepareStatement(sql);
	    		int i =1;
	    		for(Object param : params ){
	    			if(param instanceof String){
	    				pstmt.setString(i, (String)param);
	    				
	    			}
	    			else if(param instanceof Integer){
	    				pstmt.setInt(i, (Integer)param);
	    			}
	    			else if(param instanceof Long){
	    				pstmt.setLong(i, (Long)param);
	    			}
	    			else if(param instanceof Float){
	    				pstmt.setFloat(i, (Float)param);
	    			}
	    			else if(param instanceof Date){
	    				pstmt.setDate(i, new java.sql.Date(((Date) param).getTime()));
	    			}
	    			else if(param instanceof Timestamp){
	    				pstmt.setTimestamp(i, (Timestamp)param);
	    			}
	    			else if(param instanceof Double){
	    				pstmt.setDouble(i, (Double) param);
	    			}
	    			i++;
	    		}
	            return pstmt.executeUpdate()==1?true:false;
	            
	        } catch (Exception ex) {
	            Log.getInstance().error("the SQL request ( "+sql+" ) cannot be executed", ex);
	            throw ex;
	        }
	    }
		
		/**
		 * Close a connexion
		 * @return true if it succeed, false otherwise
		 */
	    public Boolean close(){
			try {
	            this.dbStatement.close();
	            this.dbConnect.close();
	            this.dbConnect.close();
	        } catch (SQLException ex) {
	            
	        }
	        return true;
		}
			
	    /**
	     * Get all active users
	     * @return the list of users
	     * @throws Exception
	     */
		@Override
		public List<User> getActiveUsers() throws Exception {
			ResultSet rs = this.exec("SELECT * FROM user WHERE active=1");
			List<User> users = new ArrayList<User>();
			while(rs.next()){
				User user = new User();
				user.setId(Long.parseLong(""+rs.getInt("id")));
				user.setActive(rs.getInt("active")==1?true:false);
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setRole(rs.getString("role"));
				
				//get only active scenarios for the current user
				List<Scenario> scenarios = this.getScenarioByUser(user);
				user.setScenarios(scenarios);
				users.add(user);
			}
			return users;
		}

		 /**
	     * Get all active scenarios
	     * @return the list of scenarios
	     * @throws Exception
	     */
		@Override
		public List<Scenario> getScenarioByUser(User user) throws Exception {
			ResultSet rs = this.exec("SELECT * FROM scenario WHERE active = 1 AND userid="+user.getId());
			List<Scenario> scenarios = new ArrayList<Scenario>();
			while(rs.next()){
				Scenario sc = new Scenario();
				sc.setId(rs.getLong("id"));
				sc.setDescription(rs.getString("description"));
				sc.setName(rs.getString("name"));
				sc.setActive(rs.getInt("active")==1?true:false);
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate("date_creation"));
				sc.setDateCreation(cal);
				if(rs.getDate("date_last_execution")!=null){
					cal.setTime(rs.getDate("date_last_execution"));
					sc.setDateLastExecution(cal);
				}
				sc.setExecMinutes(rs.getInt("execution_min"));
				sc.setUser(user);
				
				//get tasks
				List<Task> tasks = this.getTasksByScenario(sc);
				sc.setTasks(tasks);
				scenarios.add(sc);
			}
			return scenarios;
		}

		/**
		 * Get the tasks linked to a scenario
		 * @param scenario the scenario
		 * @return the list of tasks
		 * @throws exception
		 */
		@Override
		public List<Task> getTasksByScenario(Scenario scenario)
				throws Exception {
			
			ResultSet rs = this.exec("SELECT * FROM task WHERE scenarioid = "+scenario.getId()+" ORDER BY position ");
			List<Task> tasks = new ArrayList<Task>();
			while(rs.next()){
				Task t = TaskFactory.getInstance().create(rs.getString("type"));
				t.setId(rs.getLong("id"));
				t.setDescription(rs.getString("description"));
				t.setName(rs.getString("name"));
				t.setPosition(rs.getInt("position"));
				t.setOutputContentType(rs.getString("output_content"));
				t.setInputContentType(rs.getString("input_content"));
				t.setScenario(scenario);
				//get conditions
				List<Condition<?>> conditions = this.getConditions(t);
				t.setConditions(conditions);
				
				//get articles if its TaskCrawl
				if( t instanceof TaskCrawl){
					List<Article> arts = this.getArticles(t);
					TaskCrawl task = (TaskCrawl) t;
					task.setArticles(arts);
					task.setLink(rs.getString("crawl_link"));
				}
				//get tags if its TaskCrawlHtml
				if(t instanceof TaskCrawlHtml){
					List<Tag> tags = this.getTags(t);
					TaskCrawlHtml task = (TaskCrawlHtml) t;
					task.setTags(tags);
					
					//is there a multipage
					task.setMultipageRegex(rs.getString("crawl_multipages"));
					
					//multipage limit
					task.setMultipageLimit(rs.getInt("crawl_multipageLimit"));
					
					//is there a readmore
					task.setReadmoreRegex(rs.getString("crawl_readmore"));
					
				}
				
				
				//get specific info for TaskSender
				if(t instanceof TaskSender){
					TaskSender task = (TaskSender) t;
					task.setAddSource(rs.getInt("sender_add_source")==1?true:false);
				}
				
				//get specific info for TaskSenderEmail
				if(t instanceof TaskSenderEmail){
					TaskSenderEmail task = (TaskSenderEmail) t;
					task.setObject(rs.getString("sender_email_object"));
					task.setContent(rs.getString("sender_email_content"));
					String emails = rs.getString("sender_email_emails");
					String[] tab = emails.split(";");
					for(String email : tab){
						task.addEmail(email);
					}
				}//specific info for TaskSenderBlog
				else if( t instanceof TaskSenderBlog){
					TaskSenderBlog task = (TaskSenderBlog) t;
					task.setBlogEmail(rs.getString("sender_blog_email"));
					task.setBlogLink(rs.getString("sender_blog_link"));
					task.setBlogLogin(rs.getString("sender_blog_login"));
					task.setBlogPass(rs.getString("sender_blog_pass"));
					
					if( t instanceof TaskSenderBlogWordpress){
						TaskSenderBlogWordpress ta  = (TaskSenderBlogWordpress) task;
					}
				}
				
				//Get specific info for TaskAction
				if(t instanceof TaskAction){
					TaskAction task = (TaskAction)t;
					Boolean bol = false;
					if(rs.getInt("action_keep_original_content")==1)bol=true;
					task.setKeepOriginalContent(bol);
					//Get specific info for TaskActionTranslate
					if(t instanceof TaskActionTranslate){
						TaskActionTranslate taskT = (TaskActionTranslate) t;
						taskT.setOriginal_language(rs.getString("action_translate_language_original"));
						taskT.setNeeded_language(rs.getString("action_translate_language_needed"));
					}
										
				}
				tasks.add(t);
			}
			return tasks;
		}
	
		
		/**
		 * Get the condition to ignore something in a Task
		 * @param task the task
		 * @return the list of conditions 
		 * @throws Exception 
		 */
		@Override
		@SuppressWarnings("unchecked")
		public List<Condition<?>> getConditions(Task task) throws Exception{
			ResultSet rs2 = this.exec("SELECT * FROM `condition` WHERE taskid="+task.getId());
			List<Condition<?>> conditions = new ArrayList<Condition<?>>();
			while(rs2.next()){
				Condition<?> condition = ConditionFactory.getInstance().create(rs2.getString("type"));
				condition.setPositionRegex(rs2.getString("positionRegex"));
				condition.setPositionType(rs2.getString("positionType"));
				condition.setSigne(rs2.getString("signe"));
				condition.setTask(task);
				condition.setId(rs2.getLong("id"));
				if(ConditionFactory.TYPE_STRING.equals(rs2.getString("type"))){
					((Condition<String>)condition).setValue(rs2.getString("value"));
				}
				else if(ConditionFactory.TYPE_INTEGER.equals(rs2.getString("type"))){
					((Condition<Integer>)condition).setValue(Integer.parseInt(rs2.getString("value")));
				}
				else if(ConditionFactory.TYPE_DATE.equals(rs2.getString("type"))){
					Calendar cal = Calendar.getInstance();
					cal.setTime(Constants.DF_US.parse(rs2.getString("value")));
					((Condition<Calendar>)condition).setValue(cal);
				}
				else if(ConditionFactory.TYPE_HTML.equals(rs2.getString("type"))){
					((Condition<String>)condition).setValue(rs2.getString("value"));
				}
				
				conditions.add(condition);
				
			}
			rs2.close();
			return conditions;
		}
		
		/**
		 * Find the condition 
		 * @param id the id of the condition
		 * @return the condition
		 * @throws Exception 
		 */
		@Override
		@SuppressWarnings("unchecked")
		public Condition<?> findCondition(Integer id) throws Exception{
			ResultSet rs2 = this.exec("SELECT * FROM `condition` WHERE id="+id);
			
			rs2.next();
			Condition<?> condition = ConditionFactory.getInstance().create(rs2.getString("type"));
			condition.setPositionRegex(rs2.getString("positionRegex"));
			condition.setPositionType(rs2.getString("positionType"));
			condition.setSigne(rs2.getString("signe"));
			condition.setTask(null);
			condition.setId(rs2.getLong("id"));
			if(ConditionFactory.TYPE_STRING.equals(rs2.getString("type"))){
				((Condition<String>)condition).setValue(rs2.getString("valeur"));
			}
			else if(ConditionFactory.TYPE_INTEGER.equals(rs2.getString("type"))){
				((Condition<Integer>)condition).setValue(Integer.parseInt(rs2.getString("valeur")));
			}
			else if(ConditionFactory.TYPE_DATE.equals(rs2.getString("type"))){
				Calendar cal = Calendar.getInstance();
				cal.setTime(Constants.DF_US.parse(rs2.getString("valeur")));
				((Condition<Calendar>)condition).setValue(cal);
			}
			else if(ConditionFactory.TYPE_HTML.equals(rs2.getString("type"))){
				((Condition<String>)condition).setValue(rs2.getString("valeur"));
			}
			
			rs2.close();
			return condition;
			
		}
		
		/**
		 * Get tags for a task
		 * @param task the task
		 * @return the list of tags
		 */
		@Override
		public List<Tag> getTags(Task task) throws Exception{
			ResultSet rs = this.exec("SELECT * FROM tag WHERE taskid = "+task.getId());
			List<Tag> tags = new ArrayList<Tag>();
			while(rs.next()){
				Tag tag = TagFactory.getInstance().findTag(rs.getString("type"));
				tag.setName(rs.getString("name"));
				tag.setId(rs.getLong("id"));
				tag.setClasse(rs.getString("classe"));
				
				tags.add(tag);
			}
			return tags;
		}

		/**
		 * Get articles linked to the task
		 * @param task the current task
		 * @return list of articles
		 * @throws Exception 
		 */
		@Override
		public List<Article> getArticles(Task task) throws Exception{
			ResultSet rs = this.exec("SELECT * FROM article WHERE taskid="+task.getId());
			List<Article> articles= new ArrayList<Article>();
			while(rs.next()){	
				Article article=null;
				Date date = rs.getDate("date_execution");
				Calendar cal=null;
				if(date!=null){
					cal=Calendar.getInstance();
					cal.setTime(date);
				}
				article=new Article();
				article.setLink(rs.getString("link"));
				article.setId(rs.getLong("id"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setDateExecution(cal);
				article.setTask(task);
				articles.add(article);
			}
			return articles;
		}
		


		@Override
		public Boolean saveScenario(Scenario scenario) throws Exception {
			//update
			String sql = "UPDATE scenario SET date_last_execution=? WHERE id=?";
			List<Object> params = new ArrayList<Object>();
			params.add(scenario.getDateLastExecution().getTime());
			params.add(scenario.getId());
			Boolean resu = true;
			if(this.execPreparedQuery(sql,params)){
				//save all tasks 
				for(Task task : scenario.getTasks()){
					
					if(!this.saveTask(task)) resu = false;
				}
			}
			
			return resu;
		}
		

		@Override
		public Boolean saveTask(Task task) throws Exception {
			//no directly update of task
			Boolean result = true;
			if(task instanceof TaskCrawl){
				//Need to save the article
				TaskCrawl t = (TaskCrawl) task;
				for(Article article : t.getArticles()){
					if(!this.saveArticle(article))result = false;
				}
			}
			
			return result;
		}
		
		
		/**
		 * save an article
		 * @param article the article 
		 * @return true if it succeed, false otherwise
		 * @throws Exception 
		 */
		public Boolean saveArticle(Article article) throws Exception{
			if(article.getId()!=null){
				//update
				String sql = "UPDATE article SET title=?, content=?, link=?, taskid=?, date_execution=? WHERE id=?";
				List<Object> params = new ArrayList<Object>();
				params.add(article.getTitle());
				params.add(article.getContent());
				params.add(article.getLink());
				params.add(article.getTask().getId());
				params.add(Connection.DF_US.format(article.getDateExecution().getTime()));
				params.add(article.getId());
				this.execPreparedQuery(sql,params);
			}
			else{
				//insert
				String sql = "INSERT INTO article (title,content,link,date_execution,taskid) VALUES (?,?,?,?,?)";
				List<Object> params = new ArrayList<Object>();
				params.add(article.getTitle());
				params.add(article.getContent());
				params.add(article.getLink());
				params.add(Connection.DF_US.format(article.getDateExecution().getTime()));
				params.add(article.getTask().getId());
				this.execPreparedQuery(sql,params);
			}
			return true;
		}
		
		/**
		 * Presents connection module
		 * @return the string presentation
		 */
		public String toString(){
			return super.toString()+" Mysql";
		}
	    
}
