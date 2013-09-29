//package tests;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.junit.After;
//import org.junit.Test;
//
//public class Homework2Test {
//
//    public static final String BASE_URL = "http://ci.itcollege.ee/part2example/";
//    private static Pattern SESSION_ID_PATTERN = Pattern.compile("id is (\\w+)");
//    private static Pattern SESSION_COUNT_PATTERN = Pattern.compile("count: (\\d+)");
//    private static Pattern SESSION_ATTRIBUTE_PATTERN = Pattern.compile("attribute is (\\w+)");
//
//    private static String HOME_PAGE = "HomePage";
//    private static String SESSION_COUNT_PAGE = "SessionCount";
//    private static String LOG_OUT_PAGE = "LogOut";
//
//    @Test
//    public void homePageCreatesSession() {
//        String id = getSessionId(getPageSource(HOME_PAGE));
//
//        assertThat(id, is(notNullValue()));
//    }
//
//    @Test
//    public void sessionIdStaysTheSameOnReturn() {
//        WebDriver driver = getDriver();
//
//        String idA = getSessionId(getPageSource(HOME_PAGE, driver));
//        String idB = getSessionId(getPageSource(HOME_PAGE, driver));
//
//        assertThat(idA, is(notNullValue()));
//        assertThat(idA, is(equalTo(idB)));
//    }
//
//    @Test
//    public void differentUserHasDifferentSession() {
//        String idA = getSessionId(getPageSource(HOME_PAGE));
//        String idB = getSessionId(getPageSource(HOME_PAGE));
//
//        assertThat(idA, is(not(equalTo(idB))));
//    }
//
//    @Test
//    public void newSessionIncreasesSessionCount() {
//
//        int sessionCountBefore = getSessionCount(getPageSource(SESSION_COUNT_PAGE));
//
//        getPageSource(HOME_PAGE);
//
//        int sessionCountAfter = getSessionCount(getPageSource(SESSION_COUNT_PAGE));
//
//        assertThat(sessionCountBefore, is(sessionCountAfter - 1));
//    }
//
//    @Test
//    public void logOutDecreasesSessionCount() {
//
//        int sessionCountBefore = getSessionCount(getPageSource(SESSION_COUNT_PAGE));
//
//        WebDriver driver = getDriver();
//        getPageSource(HOME_PAGE, driver); // start new session
//        getPageSource(LOG_OUT_PAGE, driver); // close session
//
//        int sessionCountAfter = getSessionCount(getPageSource(SESSION_COUNT_PAGE));
//
//        assertThat(sessionCountBefore, is(equalTo(sessionCountAfter)));
//    }
//
//    @Test
//    public void sessionParametersCanBeSet() {
//
//        String paramValue = String.valueOf(System.currentTimeMillis());
//
//        WebDriver driver = getDriver();
//        getPageSource(HOME_PAGE + "?param=" + paramValue, driver);
//
//        String sessionAttribute = getSessionAttribute(getPageSource(HOME_PAGE, driver));
//
//        assertThat(sessionAttribute, is(equalTo(paramValue)));
//    }
//
//    @Test
//    public void sessionParametersAreGoneAfterLogout() {
//
//        String paramValue = String.valueOf(System.currentTimeMillis());
//
//        WebDriver driver = getDriver();
//        getPageSource(HOME_PAGE + "?param=" + paramValue, driver);
//        getPageSource(LOG_OUT_PAGE, driver);
//
//        String sessionAttribute = getSessionAttribute(getPageSource(HOME_PAGE, driver));
//
//        assertThat(sessionAttribute, is("null"));
//    }
//
//    @After
//    public void closeOpenDrivers() {
//        for (WebDriver each : openDriversDrivers) {
//            each.close();
//        }
//    }
//
//    private Set<WebDriver> openDriversDrivers = new HashSet<>();
//
//    private String getPageSource(String pageName) {
//        return getPageSource(pageName, getDriver());
//    }
//
//    private String getPageSource(String pageName, WebDriver driver) {
//        driver.get(getUrl(pageName));
//        return driver.getPageSource();
//    }
//
//    public String getSessionId(String src) {
//        return matchCommon(src, SESSION_ID_PATTERN);
//    }
//
//    public String getSessionAttribute(String src) {
//        return matchCommon(src, SESSION_ATTRIBUTE_PATTERN);
//    }
//
//    public int getSessionCount(String src) {
//        String countAsString = matchCommon(src, SESSION_COUNT_PATTERN);
//        return countAsString != null ? Integer.valueOf(countAsString) : 0;
//    }
//
//    private String matchCommon(String src, Pattern pattern) {
//        Matcher m = pattern.matcher(src);
//        if (m.find()) {
//            return m.group(1);
//        } else {
//            throw new IllegalStateException("pattern not found");
//        }
//    }
//
//    private WebDriver getDriver() {
//        WebDriver driver = new HtmlUnitDriver();
//        openDriversDrivers.add(driver);
//        return driver;
//    }
//
//    private String getUrl(String page) {
//        return BASE_URL + page;
//    }
//
//
//}