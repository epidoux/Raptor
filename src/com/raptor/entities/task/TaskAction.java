package com.raptor.entities.task;

import java.io.Serializable;

/**
 * This class represents an action task which modify something (translation, editing content...)
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
public abstract class TaskAction extends Task implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   /**
    * Keep original content
    */
   private Boolean keepOriginalContent;
   
   
   
   public Boolean getKeepOriginalContent() {
	return keepOriginalContent;
   }

	public void setKeepOriginalContent(Boolean keepOriginalContent) {
		this.keepOriginalContent = keepOriginalContent;
	}

	public abstract Object execute(Object filled) throws Exception ;
   
	@Override
	public String toString() {
		return "Action Task ";
	}
   
   
}