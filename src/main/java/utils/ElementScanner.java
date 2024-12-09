package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElementScanner {
    private WebDriver driver;
    private StringBuilder pageObjectClass;
    private Set<String> elementNames;

    public ElementScanner() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nicor\\eclipe-workspace\\Testing_UCU\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        pageObjectClass = new StringBuilder();
        elementNames = new HashSet<>();
    }

    public void scanElements(String url, String className) {
        driver.get(url);
        pageObjectClass.append("package pages;\n\n");
        pageObjectClass.append("import org.openqa.selenium.WebDriver;\n");
        pageObjectClass.append("import org.openqa.selenium.WebElement;\n");
        pageObjectClass.append("import org.openqa.selenium.support.FindBy;\n");
        pageObjectClass.append("import org.openqa.selenium.support.PageFactory;\n");
        pageObjectClass.append("import org.openqa.selenium.support.ui.ExpectedConditions;\n");
        pageObjectClass.append("import org.openqa.selenium.support.ui.WebDriverWait;\n\n");
        pageObjectClass.append("import java.time.Duration;\n\n");
        pageObjectClass.append("public class ").append(className).append(" {\n");
        pageObjectClass.append("    private WebDriver driver;\n");
        pageObjectClass.append("    private WebDriverWait wait;\n\n");

        List<WebElement> elements = driver.findElements(By.xpath("//*"));
        for (WebElement element : elements) {
            if (element.isDisplayed() && element.isEnabled()) {
                String name = element.getAttribute("name");
                String id = element.getAttribute("id");
                String alt = element.getAttribute("alt");
                String text = element.getText().replaceAll("[^a-zA-Z0-9]", "");
                String xpath = getXPath(element);

                String elementName = generateUniqueName(name, id, alt, text, elements.indexOf(element));

                pageObjectClass.append("    @FindBy(xpath = \"").append(xpath).append("\")\n");
                pageObjectClass.append("    private WebElement ").append(elementName).append(";\n\n");
            }
        }

        pageObjectClass.append("    public ").append(className).append("(WebDriver driver) {\n");
        pageObjectClass.append("        this.driver = driver;\n");
        pageObjectClass.append("        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));\n");
        pageObjectClass.append("        PageFactory.initElements(driver, this);\n");
        pageObjectClass.append("    }\n\n");

        for (WebElement element : elements) {
            if (element.isDisplayed() && element.isEnabled()) {
                String name = element.getAttribute("name");
                String id = element.getAttribute("id");
                String alt = element.getAttribute("alt");
                String text = element.getText().replaceAll("[^a-zA-Z0-9]", "");
                String methodName = generateUniqueName(name, id, alt, text, elements.indexOf(element));

                pageObjectClass.append("        wait.until(ExpectedConditions.elementToBeClickable(").append(methodName).append("));\n");
                pageObjectClass.append("        ").append(methodName).append(".click();\n");
                pageObjectClass.append("    }\n\n");
            }
        }

        pageObjectClass.append("}\n");

        try (FileWriter fileWriter = new FileWriter("C:\\Users\\nicor\\eclipe-workspace\\Testing_UCU\\ucu\\src\\main\\java\\pages\\" + className + ".java")) {
            fileWriter.write(pageObjectClass.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    private String generateUniqueName(String name, String id, String alt, String text, int index) {
        String baseName = (name != null && !name.isEmpty()) ? name :
                          (id != null && !id.isEmpty()) ? id :
                          (alt != null && !alt.isEmpty()) ? alt :
                          (!text.isEmpty()) ? text : "element" + index;

        String uniqueName = baseName;
        int counter = 1;
        while (elementNames.contains(uniqueName)) {
            uniqueName = baseName + counter;
            counter++;
        }
        elementNames.add(uniqueName);
        return uniqueName;
    }

    private String getXPath(WebElement element) {
        String js = "function getXPath(element) {" +
                "var comp, comps = [];" +
                "var parent = null;" +
                "var xpath = '';" +
                "var getPos = function(element) {" +
                "var position = 1, curNode;" +
                "if (element.nodeType == Node.ATTRIBUTE_NODE) {" +
                "return null;" +
                "}" +
                "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {" +
                "if (curNode.nodeName == element.nodeName) {" +
                "++position;" +
                "}" +
                "}" +
                "return position;" +
                "};" +
                "if (element instanceof Document) {" +
                "return '/';" +
                "}" +
                "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {" +
                "comp = comps[comps.length] = {};" +
                "switch (element.nodeType) {" +
                "case Node.TEXT_NODE:" +
                "comp.name = 'text()';" +
                "break;" +
                "case Node.ATTRIBUTE_NODE:" +
                "comp.name = '@' + element.nodeName;" +
                "break;" +
                "case Node.PROCESSING_INSTRUCTION_NODE:" +
                "comp.name = 'processing-instruction()';" +
                "break;" +
                "case Node.COMMENT_NODE:" +
                "comp.name = 'comment()';" +
                "break;" +
                "case Node.ELEMENT_NODE:" +
                "comp.name = element.nodeName;" +
                "break;" +
                "}" +
                "comp.position = getPos(element);" +
                "}" +
                "for (var i = comps.length - 1; i >= 0; i--) {" +
                "comp = comps[i];" +
                "xpath += '/' + comp.name.toLowerCase();" +
                "if (comp.position !== null) {" +
                "xpath += '[' + comp.position + ']';" +
                "}" +
                "}" +
                "return xpath;" +
                "}" +
                "return getXPath(arguments[0]).toLowerCase();";
        return (String) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(js, element);
    }

    public static void main(String[] args) {
        ElementScanner scanner = new ElementScanner();
        scanner.scanElements("https://www.google.com//", "PaginaGenerada");
    }
}


