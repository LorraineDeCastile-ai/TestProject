# 環境準備:
## SQL server:
```
參考https://learn.microsoft.com/zh-tw/sql/linux/quickstart-install-connect-docker?view=sql-server-ver16&tabs=cli&pivots=cs1-bash
拉取image: podman pull mcr.microsoft.com/mssql/server:2022-latest
啟動: podman run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=1qaz@WSX" -p 1433:1433 --name sql-servevr --hostname sql-servevr -d mcr.microsoft.com/mssql/server:2022-latest
		
登入:
進入container: podman exec -it sql-servevr "bash"
登入sa: /opt/mssql-tools18/bin/sqlcmd -No -C -S localhost -U sa -P "1qaz@WSX"
登入成功後會有提示字元1>
輸入GO執行sql
```
# 單元測試
### BatchConfigTests.syncDailyForeignExchangeRates
- 測試功能1:從指定的url獲取資料並寫入DB
### ApiControllerTests.forexAPI1
- 測試功能2:正常查詢的狀況
### ApiControllerTests.forexAPI2
- 測試功能2:參數有誤的狀況
