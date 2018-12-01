package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.UserTop;

import java.util.List;

public interface UserRepositoryCustom {

    List<UserTop> getTopUsersAsc();

    List<UserTop> getTopUsersDesc();
}
