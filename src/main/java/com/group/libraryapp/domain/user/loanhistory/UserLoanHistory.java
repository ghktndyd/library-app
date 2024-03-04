package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class UserLoanHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @JoinColumn(nullable = false)
  @ManyToOne
  private User user;

  @Getter
  private String bookName;

  private boolean isReturn;

  protected UserLoanHistory() {

  }

  public UserLoanHistory(User user, String bookName) {
    this.user = user;
    this.bookName = bookName;
    this.isReturn = false;
  }

  public void doReturn() {
    this.isReturn = true;
  }

}
