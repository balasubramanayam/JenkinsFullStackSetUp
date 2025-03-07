const { default: sonarqubeScanner } = require('sonarqube-scanner');

sonarqubeScanner(
  {
    serverUrl: 'http://localhost:9000', // Your SonarQube server URL
    token: 'sqp_b1afcf1fcb556599fd843f91bc077c58d17e6023', // Replace with your generated token
    options: {
      'sonar.projectKey': 'FrontendSonarQube', // Unique key for your project
      'sonar.projectName': 'FrontendSonarQube', // Display name
      'sonar.sources': 'src', // Source code directory
      'sonar.exclusions': 'src/**/*.test.js', // Exclude test files (optional)
      'sonar.sourceEncoding': 'UTF-8',
      'sonar.javascript.lcov.reportPaths': 'coverage/lcov.info', // For test coverage (optional, see Step 5)
    },
  },
  () => {
    console.log('SonarQube analysis completed');
    process.exit();
  }
);
