package io.github.sxh705.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sxh705.entity.Kv;
import io.github.sxh705.mapper.KvMapper;
import org.springframework.stereotype.Service;

@Service
public class KvService extends ServiceImpl<KvMapper, Kv> {
}
