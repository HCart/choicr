package com.boxcorp.choicr.controller;

import com.boxcorp.choicr.model.ChoiceList;
import com.boxcorp.choicr.service.ChoiceListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ChoiceListController {

    private final ChoiceListService choiceListService;

    public ChoiceListController(ChoiceListService choiceListService) {
        this.choiceListService = choiceListService;
    }

    @PostMapping(value = "/lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ChoiceList createChoiceList(@RequestBody ChoiceList choiceList) {

        return choiceListService.createChoiceList(choiceList);
    }

    @PutMapping(value = "/lists")
    @ResponseStatus(HttpStatus.OK)
    public ChoiceList updateChoiceList(@RequestBody ChoiceList choiceList) {

        return choiceListService.updateChoiceList(choiceList);
    }

    @GetMapping(value = "/lists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChoiceList getChoiceList(@PathVariable String id) {

        return choiceListService.getChoiceList(id);
    }
}
