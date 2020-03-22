package com.br.cadastro.reposytory;

import com.br.cadastro.model.SolrTest;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrTestRepository  extends SolrCrudRepository<SolrTest, String> {

    List<SolrTest> findByDocTitleEndsWith(String title); // find documents whose docTitle ends with specified string
    List<SolrTest> findByDocTitleStartsWith(String title); // find documents whose docTitle starts with specified string
    List<SolrTest> findByDocTypeEndsWith(String type); //find documents whose docType ends with specified string
    List<SolrTest> findByDocTypeStartsWith(String type);//find documents whose docType start with specified string
}
