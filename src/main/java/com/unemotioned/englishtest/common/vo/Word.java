package com.unemotioned.englishtest.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Word {
    private String word;
    private String def1;
    private String def2;
    private int index;
}
