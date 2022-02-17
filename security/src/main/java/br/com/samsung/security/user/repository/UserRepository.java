package br.com.samsung.security.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.samsung.security.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
