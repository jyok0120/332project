# 332-project
[CSED332] Software Design Methods Team Project

## How to run files
master 폴더 하위의 master
sbt run을 통해 프로젝트를 시작하고, vm 서버에 따라 master.main과 worker.main을 선택한다.
master 폴더 하위의 MasterArgument.scala에 max Num Worker를 지정한다.
MasterServer의 경우 IP : 2.2.2.101, Port : 1234
WorkerServer의 경우 서버마다 코드의 값을 바꿔줘야 한다.
* IP : 2.2.2.103 Port : 3333
* IP : 2.2.2.104 Port : 4444
* IP : 2.2.2.105 Port : 5555
* IP : 2.2.2.106 Port : 6666
* IP : 2.2.2.107 Port : 7777
* IP : 2.2.2.108 Port : 8888
* IP : 2.2.2.109 Port : 9999
* IP : 2.2.2.110 Port : 1111
* IP : 2.2.2.111 Port : 2222
Worker 폴더의 Main에서 address = "2.2.2.103", port = "3333" 이런식으로 되어 있는 부분을
바꾸고, Worker 폴더으 WorkerServer에서 server를 만들 때 숫자를 바꾸면 된다.



## Progress of Week 1
* Github repository 생성
* Notion으로 documentation 공유 (추후 주간 보고를 노션으로 대체할 수 있음)

## Goal of Week 2
전반적인 프로젝트의 workflow를 학습하도록 한다.
* Juyeon Ok - Learning about the VM environment
* Jinmo Kim - Finding the testing envrionment
* Hyunji Song - Analysing the overall structure of the project

앞으로 다음 notion 링크를 이용해서 주간 상황을 보고한다.
(Week1부터 모두 노션에 업로드되어 있습니다.)
https://substantial-calculator-ca6.notion.site/CSE-332-Project-b87a3fb58af847c7a97e508e7074daec
