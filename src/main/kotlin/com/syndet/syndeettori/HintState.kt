package com.syndet.syndeettori

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
class HintState {

    var counter : Int = 1

    fun addCounter() : Int {
        counter = counter + 1;
        return counter;
    }

}
