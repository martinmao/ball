/**
 * 
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scleropages.crud.dao.orm.jpa.entity.embeddable;

/**
 * @author <a href="mailto:dev.martinmao@gmail.com">Martin Mao</a>
 *
 */
public enum Gender {

	FEMALE("Female", "女"), MALE("Male", "男"), UNKOWN("Unkown", "未知");

	private String tag;
	private String localTag;
	

	private Gender(String tag, String localTag) {
		this.tag = tag;
		this.localTag = localTag;
	}

	public String tag() {
		return tag;
	}

	public String localTag() {
		return localTag;
	}
}
