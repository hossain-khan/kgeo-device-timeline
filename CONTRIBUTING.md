# Contributing to Google Device Timeline JSON Parser

Thank you for your interest in contributing to this project! We welcome contributions of all kinds.

## How to Contribute

### Reporting Issues

If you find a bug or have a suggestion for improvement:

1. Check if the issue already exists in our [issue tracker](https://github.com/hossain-khan/kgeo-device-timeline/issues)
2. If not, create a new issue with:
   - A clear and descriptive title
   - Steps to reproduce the issue (for bugs)
   - Expected vs actual behavior
   - Your environment details (OS, JDK version, etc.)
   - Sample data or code if applicable (please anonymize any personal data)

### Contributing Code

#### Getting Started

1. Fork the repository
2. Clone your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/kgeo-device-timeline.git
   cd kgeo-device-timeline
   ```
3. Create a new branch for your feature/fix:
   ```bash
   git checkout -b feature/your-feature-name
   ```

#### Development Setup

**Prerequisites:**
- JDK 21 or higher
- Git

**Build and Test:**
```bash
# Build the project
./gradlew build

# Run tests
./gradlew test

# Generate documentation
./gradlew :lib:dokkaGeneratePublicationHtml
```

#### Making Changes

1. **Code Style**: 
   - Follow Kotlin coding conventions
   - Use meaningful variable and function names
   - Add KDoc comments for public APIs
   - Keep line length under 120 characters

2. **Testing**:
   - Add tests for new functionality
   - Ensure all existing tests pass
   - Aim for good test coverage

3. **Documentation**:
   - Update README.md if you're adding new features
   - Add KDoc comments for public APIs
   - Update sample code if applicable

#### Submitting Changes

1. Commit your changes:
   ```bash
   git add .
   git commit -m "feat: add support for new timeline feature"
   ```

2. Push to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```

3. Create a Pull Request:
   - Use a clear title and description
   - Reference any related issues
   - Include screenshots for UI changes (if applicable)

### Commit Message Guidelines

We follow [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` new features
- `fix:` bug fixes  
- `docs:` documentation changes
- `style:` formatting, missing semicolons, etc.
- `refactor:` code changes that neither fix bugs nor add features
- `test:` adding or updating tests
- `chore:` maintenance tasks

Examples:
```
feat: add support for parsing activity confidence scores
fix: handle null values in location data gracefully
docs: update API documentation with new examples
```

## Code of Conduct

This project follows a Code of Conduct to ensure a welcoming environment for all contributors:

- Be respectful and inclusive
- Welcome newcomers and help them get started
- Focus on constructive feedback
- Respect different viewpoints and experiences

## Getting Help

- 💬 Start a [Discussion](https://github.com/hossain-khan/kgeo-device-timeline/discussions) for questions
- 🐛 Create an [Issue](https://github.com/hossain-khan/kgeo-device-timeline/issues) for bugs
- 📖 Check the [Documentation](https://hossain-khan.github.io/kgeo-device-timeline/)

## License

By contributing to this project, you agree that your contributions will be licensed under the MIT License.

Thank you for contributing! 🎉