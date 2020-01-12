package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.domain.Account;
import com.example.demo.domain.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AccountRepositoryTest {

  @Autowired
  AccountRepository repository;

  @Test
  public void crud() {
    Account account = new Account();
    account.setName("kts");
    account.setUserId("1");
    account.setPassword("kts");

    repository.save(account);

    Optional<Account> byId = repository.findById(account.getUserId());
    assertThat(byId.orElseGet(() -> new Account())).isEqualTo(account);

    Optional<Account> byId2 = repository.findById("adfasdfdf");

    assertThat(byId2.orElseGet(() -> new Account())).isEqualTo(new Account());
  }

  @Test
  public void getTagUser() {
    String tag = "aaa";

    Account account = new Account();
    account.getTags().add("aaa");

    repository.save(account);

    Account account2 = new Account();
    account2.getTags().add("b");

    repository.save(account2);

    Account account3 = new Account();
    account3.getTags().add("aaa");
    repository.save(account3);

    List<Account> byFavoritesContains = repository.findByTagsContains(tag);
    assertThat(byFavoritesContains.size()).isEqualTo(2);
  }

  @Test
  public void getUsers() {
    long total = repository.count();

    int size = repository.findAll().size();

    assertThat(total).isEqualTo(size);

  }

  @Test
  public void getUserProfile() {

    Account account = new Account();
    account.setUserId("1");
    account.getProfiles().add("AI");
    repository.save(account);

    assertThat(repository.findById(account.getUserId()).get().getProfiles()).contains("AI");
  }

  @Test
  public void addTag() {

    Account account = new Account();
    account.setUserId("1");
    account.getProfiles().add("AI");
    account.getTags().add("tag1");
    repository.save(account);

    List<String> newTags = new ArrayList<>();
    newTags.add("new1");
    newTags.add("new2");

    Account account1 = repository.findById(account.getUserId()).get();
    account1.getTags().addAll(newTags);
    repository.save(account1);

    assertThat(repository.findById(account1.getUserId()).get().getTags().size()).isEqualTo(3);
  }

  @Test
  public void messageTest() {
    Message message = new Message();
    message.setTitle("title");
    message.setContent("content");

    Account account = new Account();
    account.setUserId("kts1");
    account.getMessages().add(message);

    repository.save(account);

    Account account1 = repository.findById(account.getUserId()).get();

    assertThat(account1.getMessages().size()).isEqualTo(1);
  }

}