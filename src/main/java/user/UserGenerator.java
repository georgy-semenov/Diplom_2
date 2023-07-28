package user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public User random(){
        return new User(RandomStringUtils.randomAlphabetic(3,6) +"@mail.ru", "123456", RandomStringUtils.randomAlphabetic(3,6));
    }
    public User genericWithOutEmail(){
        return new User("", "12345", "abvd");
    }
    public User genericWithOutPassword(){
        return new User(RandomStringUtils.randomAlphabetic(3,6) +"@mail.ru", "",RandomStringUtils.randomAlphabetic(3,6));
    }
    public User genericWithOutName(){
        return new User(RandomStringUtils.randomAlphabetic(3,6) +"@mail.ru","123456", "");
    }
    public User generic(){
        return new User(RandomStringUtils.randomAlphabetic(3,6) +"@mail.ru", "123456");
    }
}
