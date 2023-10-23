#configure postgres
mkdir ~/postgres_data
initdb ~/postgres_data
pg_ctl -D ~/postgres_data start
createuser --superuser --pwprompt postgres/postgres
createdb ostock_dev
psql -d ostock_dev

#start
pg_ctl -D ~/postgres_data -l ~/logfile start

