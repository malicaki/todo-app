FROM ubuntu:latest
LABEL authors="malic"

ENTRYPOINT ["top", "-b"]