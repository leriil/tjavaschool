package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.UserTop;

import java.util.List;

public interface UserRepositoryCustom {

    public List<UserTop> getTopUsersAsc();

    public List<UserTop> getTopUsersDesc();
}
