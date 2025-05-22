#!/bin/sh
set -e

# Первый аргумент — хост
host="$1"
shift


until PGPASSWORD=$DB_PASSWORD psql -h "$host" -p 5432 -U "$DB_USER" -d "$DB_NAME" -c '\q'; do
  >&2 echo "PostgreSQL недоступен - ждём 2 секунды..."
  sleep 2
done

>&2 echo "PostgreSQL готов - запускаем приложение"
exec "$@"