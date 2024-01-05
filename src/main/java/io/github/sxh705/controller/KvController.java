package io.github.sxh705.controller;

import io.github.sxh705.entity.Kv;
import io.github.sxh705.service.KvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kv")
public class KvController {
    @Autowired
    KvService kvService;

    @GetMapping("get/{key}")
    String gt(@PathVariable("key") String key) {
        Kv kv = kvService.lambdaQuery()
                .select(Kv::getV)
                .eq(Kv::getK, key)
                .one();
        if (kv == null) {
            return null;
        } else {
            return kv.getV();
        }
    }

    @PostMapping("set")
    Boolean st(@RequestBody Kv kv) {
        return kvService.saveOrUpdate(kv);
    }

}
