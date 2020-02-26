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
import java.util.Calendar;
import java.util.List;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.components.model.Authors;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

@HippoEssentialsGenerated(internalName = "bloomreachsearchproject:blogpost")
@Node(jcrType = "bloomreachsearchproject:blogpost")
public class Blogpost extends HippoDocument implements Authors {

    public static final String TITLE = "bloomreachsearchproject:title";
    public static final String INTRODUCTION = "bloomreachsearchproject:introduction";
    public static final String CONTENT = "bloomreachsearchproject:content";
    public static final String PUBLICATION_DATE = "bloomreachsearchproject:publicationdate";
    public static final String CATEGORIES = "bloomreachsearchproject:categories";
    public static final String AUTHOR = "bloomreachsearchproject:author";
    public static final String AUTHOR_NAMES = "bloomreachsearchproject:authornames";
    public static final String LINK = "bloomreachsearchproject:link";
    public static final String AUTHORS = "bloomreachsearchproject:authors";
    public static final String TAGS = "hippostd:tags";

   @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:publicationdate")
    public Calendar getPublicationDate() {
        return getSingleProperty(PUBLICATION_DATE);
    }

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:authornames")
    public String[] getAuthorNames() {
        return getMultipleProperty(AUTHOR_NAMES);
    }

    public String getAuthor() {
        final String[] authorNames = getAuthorNames();
        if(authorNames !=null && authorNames.length > 0){
            return authorNames[0];
        }
        return null;
    }

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:title")
    public String getTitle() {
        return getSingleProperty(TITLE);
    }

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:introduction")
    public String getIntroduction() {
        return getSingleProperty(INTRODUCTION);
    }

    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:categories")
    public String[] getCategories() {
        return getMultipleProperty(CATEGORIES);
    }

    @Override
    @HippoEssentialsGenerated(internalName = "bloomreachsearchproject:authors")
    public List<Author> getAuthors() {
        return getLinkedBeans(AUTHORS, Author.class);
    }
}