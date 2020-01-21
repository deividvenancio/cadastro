package com.br.cadastro.service.impl;

import com.br.cadastro.model.Sequence;
import com.br.cadastro.reposytory.SequenceRepository;
import com.br.cadastro.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceServiceImpl implements SequenceService {
    @Autowired
    SequenceRepository sequenceRepository;

    @Override
    public long sequenceKey(String chave) {

        Sequence sequence = sequenceRepository.findByChave(chave);
        if (sequence == null) {
            sequence = new Sequence(chave, 1);
            sequenceRepository.save(sequence);
        } else {
            sequence.setSequence(sequence.getSequence() + 1) ;
            sequenceRepository.save(sequence);
        }
        return sequence.getSequence();
    }
}
