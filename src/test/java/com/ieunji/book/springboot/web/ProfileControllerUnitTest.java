package com.ieunji.book.springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {

  @Test
  public void real_prorile이_조회된다() {
    //given
    String expectedProfiles = "real";
    MockEnvironment env = new MockEnvironment();
    env.addActiveProfile(expectedProfiles);
    env.addActiveProfile("oauth");
    env.addActiveProfile("real-db");

    ProfileController controller = new ProfileController(env);

    //when
    String profile = controller.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfiles);
  }

  @Test
  public void real_profile이_없으면_첫_번째가_조회된다() {
    //given
    String expectedProfiles = "oauth";
    MockEnvironment env = new MockEnvironment();
    env.addActiveProfile(expectedProfiles);
    env.addActiveProfile("real-db");

    ProfileController controller = new ProfileController(env);

    //when
    String profile = controller.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfiles);
  }

  @Test
  public void active_profile이_없으면_default가_조회된다() {
    //given
    String expectedProfile = "default";
    MockEnvironment env = new MockEnvironment();

    ProfileController controller = new ProfileController(env);

    //when
    String profile = controller.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfile);
  }
}
