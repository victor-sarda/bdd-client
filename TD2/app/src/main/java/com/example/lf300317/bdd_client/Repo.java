package com.example.lf300317.bdd_client;

/**
 * Created by lf300317 on 01/12/2015.
 */
public class Repo {
    final String name;
    final String created_at;
    final String html_url;
    final String description;
    final int open_issues_count;

    public Repo(String name, String created_at, String html_url,String description, int open_issues_count) {
        this.name = name;
        this.created_at = created_at;
        this.html_url = html_url;
        this.description = description;
        this.open_issues_count = open_issues_count;
    }
}
