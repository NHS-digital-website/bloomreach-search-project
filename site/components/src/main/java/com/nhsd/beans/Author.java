package com.nhsd.beans;

/*
 * Copyright 2014-2019 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.List;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.onehippo.cms7.essentials.components.model.AuthorEntry;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

@HippoEssentialsGenerated(internalName = "bloomreachsearchproject:author")
@Node(jcrType = "bloomreachsearchproject:author")
public class Author extends HippoDocument implements AuthorEntry {

    public static final String ROLE = "bloomreachsearchproject:role";
    public static final String ACCOUNTS = "bloomreachsearchproject:accounts";
    public static final String FULL_NAME = "bloomreachsearchproject:fullname";
    public static final String IMAGE = "bloomreachsearchproject:image";
    public static final String CONTENT = "bloomreachsearchproject:content";

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:fullname")
    public String getFullName() {
        return getSingleProperty(FULL_NAME);
    }

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:role")
    public String getRole() {
        return getSingleProperty(ROLE);
    }

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean(IMAGE, HippoGalleryImageSet.class);
    }

  	@HippoEssentialsGenerated(internalName = "bloomreachsearchproject:accounts")
	  public List<Account> getAccounts() {
		    return getChildBeansByName(ACCOUNTS, Account.class);
	  }
}
