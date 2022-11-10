# Contributing

이 Repository는 Life Game 기능 확장 및 설계 개선 개발 프로젝트의 Repository입니다.
Contribute 하려는 Repo가 해당 Repo가 맞는지 확인을 꼭 해주시기 바랍니다.

## 1. Fork and Clone

모든 contribution은 fork한 레포를 통해 진행합니다.
그러므로 `CAU-DesignPattern/LifeGame` 레포에서 개인 레포로 fork 해줍니다.

그리고 개인 repo를 로컬 저장소로 clone 해줍니다.

## 2. Make new branch

develop 브랜치에서, 개별 개발용 branch를 생성합니다.<br/>
branch명에 대한 규칙은 별도로 없으나, `(커밋 타입)/(개발 내용)`의 형태를 따르는 것을 추천드립니다.
커밋 타입은 하단 *3번 항목*을 참조 부탁드립니다.

## 3. Develop and Commit

개발이 완료 된 후에, Commit을 남겨주며, Commit에 대한 규칙은 다음과 같습니다.

#### - commit 규칙

```
[{브랜치 이름}](#{이슈 존재시 이슈번호}) message
detail: {변경한 폴더 or 파일명}

ex. [feature/login](#1) Add user clean function
    detail: {폴더 위치}
            - User.java
```

#### - commit 타입

[Gitflow Workflow](https://blog.dnd.ac/types-of-git-branch/)<br/>
<image src=https://blog.dnd.ac/assets/images/types-of-git-branch/total-branch.png width="300">

```
master: 제품으로 출시될 수 있는 브랜치
develop: 다음 출시 버전을 개발하는 브랜치
feature: 기능을 개발하는 브랜치
* feature/{기능}
* issue가 있는 경우, [feature/{기능}](#{이슈번호}) message
ops: 프로젝트 운영에 쓰이는 브랜치
chore: 문서 등 기타 단순 수정하는 브랜치
* [chore/{한 일}]
fix: 이슈를 반영하여 수정하는 브랜치
* [fix/{한 일}]
* [fix/{한 일}](#{이슈번호}) message
release: 이번 출시 버전을 준비하는 브랜치
hotfix: 출시 버전에서 발생한 버그를 수정하는 브랜치
```

## 4. Pull Request

기본적으로 [merge pull request](https://velog.io/@viiviii/Git-pull-request-시-merge-종류) 방법을 사용하며, Pull Request에 대한 규칙은 다음과
같습니다.

#### - PR 규칙

```
[{브랜치 이름}] close #{이슈번호}
detail: {detailed message}

ex. [feature/login] close #2, #3
    detail: {친절하고 상세한 설명}
```

`CAU-DesignPattern/LifeGame` 레포에 Pull Request를 PR Template에 맞게 작성 후 올립니다.
Merge는 1인 이상의 review를 받아야합니다.
