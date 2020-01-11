package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Message;
import com.example.demo.domain.TagScore;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;


    @Override
    public Account getUserInfo(String userId) {
        return repository.findById(userId).orElseGet(() -> new Account());
    }

    @Override
    public Account signup(Account account) {
        return repository.save(account);
    }

    @Override
    public List<Account> getUsersByTag(String tag) {
        return repository.findByFavoritesContains(tag);
    }

    @Override
    public List<Account> getUsers() {
        return repository.findAll();
    }

    @Override
    public List<String> getUserProfile(String userId) {
        return getUserInfo(userId).getProfiles();
    }

    @Override
    public List<Account> getUsersByProfile(String profile) {
        return repository.findByProfilesContains(profile);
    }

    @Override
    public Account addTagsToUser(String userId, List<String> favorites) {
        Account account = repository.findById(userId).get();
        account.getFavorites().addAll(favorites);
        return repository.save(account);
    }

    @Override
    public Account addProfilesToUser(String userId, List<String> profiles) {
        Account account = repository.findById(userId).get();
        account.getProfiles().addAll(profiles);
        return repository.save(account);
    }

    public void checkTagScore(List<TagScore> tagScores, TagScore newTag) {
        for (int i = 0; i < tagScores.size(); i++) {
            if (tagScores.get(i).getTagName().equals(newTag.getTagName())) {
                tagScores.get(i).setScore((tagScores.get(i).getScore() + newTag.getScore()) / 2);
                return;
            }
        }
        tagScores.add(newTag);
    }

    @Override
    public Account updateTagScores(TagScore newTags, String userId) {
        Account account = repository.findById(userId).get();
        List<TagScore> tagScores = account.getTagScores();

        checkTagScore(tagScores, newTags);
        return repository.save(account);
    }

    @Override
    public Account sendMessage(Message message, String userId) {
        Account account = repository.findById(userId).get();
        account.getMessages().add(message);
        return repository.save(account);
    }

    @Override
    public Account deleteMessage(String userId, Integer messageId) {
        Account account = repository.findById(userId).get();
        if (!account.getMessages().isEmpty()) {
            account.getMessages().remove(messageId - 1);
        }
        return repository.save(account);
    }
}
