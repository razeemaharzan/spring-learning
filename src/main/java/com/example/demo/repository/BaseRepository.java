package com.example.demo.repository;

import com.example.demo.domain.DomainEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends DomainEntity> extends JpaRepository<T, Long> {
    default Page<T> findAll(T entity, Pageable pageable) {
        Example<T> example = Example.of(entity, ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return this.findAll(example, pageable);
    }
}
