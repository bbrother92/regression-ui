#!/bin/bash
# script for generating allure report in target/
echo -e "\033[1;35m generating allure report... \033[0m" &&\
cd target && allure generate --clean
