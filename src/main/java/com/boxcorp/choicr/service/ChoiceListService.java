package com.boxcorp.choicr.service;

import com.boxcorp.choicr.model.ChoiceList;

public interface ChoiceListService {

    ChoiceList createChoiceList(ChoiceList choiceList);

    ChoiceList updateChoiceList(ChoiceList choiceList);

    ChoiceList getChoiceList(String id);
}
