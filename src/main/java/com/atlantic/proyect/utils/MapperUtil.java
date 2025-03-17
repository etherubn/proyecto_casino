package com.atlantic.proyect.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperUtil {
    private final ApplicationContext applicationContext;
    private ModelMapper getModelMapper() {
        return applicationContext.getBean("defaultMapper",ModelMapper.class);
    };

    public <S,T> List<T> map(List<S> source, Class<T> target) {
        return source.stream().map(element-> getModelMapper().map(element, target)).toList();
    }

    public <S,T> T map(S source, Class<T> target) {
        return getModelMapper().map(source, target);
    }
}
