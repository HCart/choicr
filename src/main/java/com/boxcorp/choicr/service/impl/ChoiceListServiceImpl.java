package com.boxcorp.choicr.service.impl;

import com.boxcorp.choicr.model.ChoiceList;
import com.boxcorp.choicr.model.Entry;
import com.boxcorp.choicr.repository.ChoiceListRepository;
import com.boxcorp.choicr.service.ChoiceListService;
import com.boxcorp.choicr.service.SequenceGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ChoiceListServiceImpl implements ChoiceListService {

    private final ChoiceListRepository choiceListRepository;

    private final SequenceGeneratorService sequenceGenerator;

    public ChoiceListServiceImpl(ChoiceListRepository choiceListRepository, SequenceGeneratorService sequenceGenerator) {
        this.choiceListRepository = choiceListRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public ChoiceList createChoiceList(ChoiceList choiceList) {

        String name = choiceList.getName();
        if (choiceListRepository.existsByName(name)) {
            //todo better exceptions
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "List already exists");
        }
        choiceList.setId(sequenceGenerator.generateSequence(ChoiceList.SEQUENCE_NAME));
        choiceList.setChoice(getChoice(choiceList));
        return choiceListRepository.save(choiceList);
    }

    @Override
    public ChoiceList updateChoiceList(ChoiceList choiceList) {
        if(choiceList.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing id");
        }

        ChoiceList previousList = choiceListRepository.findById(choiceList.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "List " + choiceList.getId() + " does not exist"));

        String name = choiceList.getName();
        if (!name.equals(previousList.getName()) && choiceListRepository.existsByName(name)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "List name already exists");
        }

        choiceList.setChoice(getChoice(choiceList));
        return choiceListRepository.save(choiceList);
    }

    @Override
    public ChoiceList getChoiceList(String id) {
        return choiceListRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "List " + id + " does not exist"));
    }

    public Boolean getChoice(ChoiceList choiceList){
        int positiveSum = choiceList.getPositiveEntries().stream() .mapToInt(Entry::getWeight).sum();
        int negativeSum = choiceList.getNegativeEntries().stream() .mapToInt(Entry::getWeight).sum();

        return positiveSum - negativeSum >= 0;
    }
}
