help: ## Show this help
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -E 's/:.*##/:\t/g'

up: ## Start the stack
	make build-app
	make run-app

database: ## Start postgres
	@docker-compose up

build-app: ## Build application
	@./gradlew clean build flywayBaseline flywayMigrate

run-app: ## Run application
	@./gradlew run

test-app: ## Execute application tests
	@./gradlew clean test

stop: ## Stop the stack
	@docker-compose down
	@echo "Containers stopped!"
