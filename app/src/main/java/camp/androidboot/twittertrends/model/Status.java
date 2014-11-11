package camp.androidboot.twittertrends.model;

/**
 *
 */
public class Status {

  private long id;
  private String text;
  private String createdAt;
  private User user;

  public long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUserName() {
    return user.getName();
  }

  public String getUserScreenName() {
    return user.getScreenName();
  }

  public String getUserProfileImageUrl() {
    return user.getProfileImageUrl();
  }

  public String getUserProfileLinkColor() {
    return user.getProfileLinkColor();
  }
}
