# SonarQube Java Lab Project

A small Maven-based Java banking application for the SonarQube lab experiment.

## Requirements
- JDK 17 or later
- Apache Maven 3.8+
- SonarQube Server running at `http://localhost:9000`

## 1. Open the project
Extract the ZIP file and open the `SonarQube_Java_Lab` folder in IntelliJ IDEA, Eclipse, or VS Code.

Check the installations:
```bash
java -version
mvn -version
```

## 2. Build and test the Java project
Run these commands from the folder containing `pom.xml`:
```bash
mvn clean test
mvn clean package
```

Run the sample application:
```bash
java -cp target/classes com.lab.banking.Main
```

Expected application output:
```text
Account holder: Asha
Current balance: 5750.0
Total accounts: 1
```

## 3. Start SonarQube
Start your local SonarQube server using the method used in your lab (for example, the SonarQube distribution's `bin` startup script or your configured Docker container).

Open:
`http://localhost:9000`

Sign in with your configured account. On a first-time local installation, follow the setup wizard to change the initial credentials.

## 4. Create an analysis token
In SonarQube, open **My Account → Security → Generate Tokens**.
Create a project-analysis token and copy it. Treat it like a password; do not commit it to source control.

## 5. Run the analysis with Maven (recommended)
In Windows Command Prompt or PowerShell, from the project folder, run:
```bash
mvn clean verify sonar:sonar -Dsonar.token=YOUR_TOKEN
```
// mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar "-Dsonar.token=$env:SONAR_TOKEN"

If your server is not at the default address, add:
```bash
-Dsonar.host.url=http://localhost:9000
```

Replace `YOUR_TOKEN` with the token you generated. Do not include angle brackets.

For a safer approach, set an environment variable first:
PowerShell:
```powershell
$env:SONAR_TOKEN="paste-your-token-here"
mvn clean verify sonar:sonar -Dsonar.token=$env:SONAR_TOKEN
```

Command Prompt:
```cmd
set SONAR_TOKEN=paste-your-token-here
mvn clean verify sonar:sonar -Dsonar.token=%SONAR_TOKEN%
```

## 6. View the dashboard
After a successful scan, open `http://localhost:9000` and select **Projects → SonarQube Java Lab** (or the project key `sonarqube-java-lab`).

Review:
- **Bugs:** defects that may cause incorrect behavior.
- **Vulnerabilities:** security weaknesses that could be exploitable.
- **Code Smells:** maintainability problems that make code harder to change.
- **Duplications:** repeated blocks of code.
- **Coverage:** percentage of code covered by tests, if coverage data is configured and imported.

The exact issue counts depend on your SonarQube version, quality profile, and rules. Do not expect fixed counts.

## 7. Important configuration values
| Property | Purpose |
|---|---|
| `sonar.projectKey` | Unique project identifier |
| `sonar.projectName` | Display name in dashboard |
| `sonar.projectVersion` | Project version |
| `sonar.sources` | Main source directory |
| `sonar.tests` | Test source directory |
| `sonar.java.binaries` | Compiled `.class` files (required by CLI for Java analysis) |
| `sonar.host.url` | SonarQube server URL |
| `sonar.token` | Authentication token; pass securely at runtime |
| `sonar.sourceEncoding` | Source file encoding |

## 8. Common errors and fixes
**Cannot connect to server / connection refused**
- Confirm SonarQube is running.
- Open `http://localhost:9000` in a browser.
- Check the host URL and port; confirm no firewall or container-port issue.

**Not authorized / invalid token**
- Generate a new token and use it in the command.
- Check that the token belongs to a user with permission to analyze the project.
- Avoid extra spaces and do not use an expired/revoked token.

**Project key missing or duplicate**
- Set a unique `sonar.projectKey` in `pom.xml` or scanner properties.
- Use the same key when viewing the dashboard.

**Java bytecode not provided**
- Run Maven analysis with `mvn clean verify sonar:sonar`, which compiles the project first.
- With SonarScanner CLI, build first and set `sonar.java.binaries=target/classes`.

**Maven command not recognized**
- Install Maven and add its `bin` directory to PATH.
- Reopen the terminal and verify with `mvn -version`.

## 9. Which issue should be fixed first?
Prioritize confirmed security vulnerabilities and critical bugs because they can expose data or break banking operations. Then address major reliability bugs, followed by code smells and duplication based on severity and impact. Re-run the analysis after fixes to verify the dashboard changes.

## Notes
- This is a teaching/demo project, not production banking software.
- For real banking software, use `BigDecimal` for monetary values, authentication/authorization, secure persistence, audit logging, and additional validation.
- The included `sonar-project.properties` is for SonarScanner CLI. The Maven command primarily uses the Maven properties in `pom.xml`.
