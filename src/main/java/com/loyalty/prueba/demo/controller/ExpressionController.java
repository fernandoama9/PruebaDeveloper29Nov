package com.loyalty.prueba.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.prueba.demo.interfaces.IProcess;
import com.loyalty.prueba.demo.pojo.ExpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devapps")
public class ExpressionController {
    private IProcess<ResponseEntity<?>, ExpRequest> iEvaluateProcess;
    private Logger log;

    @Autowired
    public ExpressionController(IProcess<ResponseEntity<?>, ExpRequest> iEvaluateProcess) {
        this.iEvaluateProcess = iEvaluateProcess;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @PostMapping("/evaluate")
    public ResponseEntity<?> evaluate(@RequestBody ExpRequest request){
        return iEvaluateProcess.process(request);
    }
}
