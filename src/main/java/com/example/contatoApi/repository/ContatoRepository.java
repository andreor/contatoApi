package com.example.contatoApi.repository;

import com.example.contatoApi.model.Contato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, String> {

}