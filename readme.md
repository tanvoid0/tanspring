# Run Project

`docker-compose up --build --remove-orphans`

# Documentation Guide

https://www.baeldung.com/spring-rest-openapi-documentation

# Authentication using JWT Guide

https://medium.com/@akhileshanand/spring-boot-api-security-with-jwt-and-role-based-authorization-fea1fd7c9e32

# Bug

- Career not generating and linking id to child career (academic/achievement database)

# Plans

- [ ] Token based Access to data
- [ ] Portfolio
  - [x] Online Judge
  - [ ] Project
- [x] Organisation
  - [x] Academic
  - [x] Achievement
  - [x] Certificate
  - [x] Experience
  - [x] Volunteer
- [x] Skill
  - [x] Hard Skills
    - [x] Skill Item
  - [x] Soft Skills
  - [x] Frameworks
    - [x] Language
- [x] Hobbies
- [x] Socials
- [ ] Better Exception Handling

# Models

- Skills
  - BaseSkill
  - SkillEntity
    - Set<SkillHard>
      - name
      - Set<SkillItem>
        - Skills
    - Set<SkillSoft>
      - BaseSkill
    - Set<SkillFramework>
      - Set<SkillLanguage>
      - Set<SkillLibrary>

- Portfolio
  - Set<OnlineJudge>
  - Set<Project>