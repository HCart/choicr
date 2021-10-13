package com.boxcorp.choicr.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
public class ChoiceList {

    @Transient
    public static final String SEQUENCE_NAME = "choiceList_sequence";

    @Id
    private String id;
    private String name;
    private List<Entry> positiveEntries;
    private List<Entry> negativeEntries;
    private Boolean choice;
}

/*


    "name": "My First list",
    "columns" : [
        {
            "name": "Aller à la piscine avec George",
            "positiveEntries": [
                {
                    "text" : "aller faire les courses",
                    "weighting": 10
                }

            ],
            "negativeEntries": [

            ]
        }
    ]


 */
