package com.example.lf300317.bdd_client;

import io.realm.RealmObject;

/**
 * Created by Pierre on 05/12/2015.
 */
public class RepoBase extends RealmObject{
    private String name;
    private String created_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(int open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    private String html_url;
    private String description;
    private int open_issues_count;

    public RepoBase(String name, String created_at, String html_url, String description, int open_issues_count) {
        this.name = name;
        this.created_at = created_at;
        this.html_url = html_url;
        this.description = description;
        this.open_issues_count = open_issues_count;
    }
    public RepoBase()
    {
    }
}
