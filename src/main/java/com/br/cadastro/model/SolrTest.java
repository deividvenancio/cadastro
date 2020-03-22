package com.br.cadastro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@AllArgsConstructor
@SolrDocument(collection = "mycol1")
public class SolrTest {

    @Id
    @Field
    private String id;

    @Field
    private String docType;

    @Field
    private String docTitle;

    @Field
    private long data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }
}
