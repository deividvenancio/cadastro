package com.br.cadastro.controller;

import com.br.cadastro.model.SolrTest;
import com.br.cadastro.reposytory.SolrTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/solr")
public class SolrTestController {

    @Autowired
    private SolrTestRepository solrTestRepository;

    @RequestMapping("/")
    public String SpringBootSolrExample() {
        return "Welcome to Spring Boot solr Example";
    }

    @RequestMapping("/delete")
    public String deleteAllDocuments() {
        try { //delete all documents from solr core
            solrTestRepository.deleteAll();
            return "documents deleted succesfully!";
        }catch (Exception e){
            return "Failed to delete documents";
        }
    }

    @RequestMapping("/save")
    public String saveAllDocuments() {
        //Store Documents
        solrTestRepository.saveAll(Arrays.asList(
                new SolrTest("1", "pdf","Java Dev Zone", 30),
                new SolrTest("2", "msg", "subject:reinvetion", 45),
                new SolrTest("3", "pdf", "Spring boot sessions", 60),
                new SolrTest("4", "docx", "meeting agenda",98),
                new SolrTest("5", "docx", "Spring boot + solr", 65)))   ;
        return "5 documents saved!!!";
    }

    @RequestMapping("/getAll")
    public List<SolrTest> getAllDocs() {
        List<SolrTest> documents = new ArrayList<>();
        // iterate all documents and add it to list
        for (SolrTest doc : this.solrTestRepository.findAll()) {
            documents.add(doc);
        }
        return documents;
    }

}
