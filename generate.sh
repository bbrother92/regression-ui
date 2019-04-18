#!/bin/bash

echo -e "\033[1;35m generating allure report... \033[0m" &&\
cd target && allure generate --clean
