import threading
import psycopg2
import time

#1) Lost-update (реалізація що втрачатиме значення)
def LostUpdate(thread_id):
    conn = psycopg2.connect(
        dbname="lab1",
        user="postgres",
        password="123",
        host="localhost",
        port="5432",
        options='-c client_encoding=UTF8'
    )
    cursor = conn.cursor()

    for i in range(10_000):
        cursor.execute("SELECT counter FROM user_counter WHERE user_id = 1")
        counter = cursor.fetchone()[0]

        counter = counter + 1
        cursor.execute("UPDATE user_counter SET counter = %s WHERE user_id = %s", (counter, 1))
        conn.commit()

    cursor.close()
    conn.close()
    print(f"Потік {thread_id} завершив роботу")

#2) In-place update
def InPlaceUpdate(thread_id):
    conn = psycopg2.connect(
        dbname="lab1",
        user="postgres",
        password="123",
        host="localhost",
        port="5432",
        options='-c client_encoding=UTF8'
    )
    cursor = conn.cursor()

    for i in range(10_000):
        cursor.execute("update user_counter set counter = counter + 1 where user_id = %s", (1,))
        conn.commit()

    cursor.close()
    conn.close()
    print(f"Потік {thread_id} завершив роботу")

#3) Row-level locking
def RowLevelLocking(thread_id):
    conn = psycopg2.connect(
        dbname="lab1",
        user="postgres",
        password="123",
        host="localhost",
        port="5432",
        options='-c client_encoding=UTF8'
    )
    cursor = conn.cursor()

    for i in range(10_000):
        cursor.execute("SELECT counter FROM user_counter WHERE user_id = 1 FOR UPDATE")
        counter = cursor.fetchone()[0]
        
        new_counter = counter + 1
        
        cursor.execute(
            "UPDATE user_counter SET counter = %s WHERE user_id = %s",
            (new_counter, 1)
        )
        conn.commit()

    cursor.close()
    conn.close()
    print(f"Потік {thread_id} завершив роботу")

#4) Optimistic concurrency control
def OptimisticConcurrencyControl(thread_id):
    conn = psycopg2.connect(
        dbname="lab1",
        user="postgres",
        password="123",
        host="localhost",
        port="5432",
        options='-c client_encoding=UTF8'
    )
    cursor = conn.cursor()

    for i in range(10_000):  # 10_000 ітерацій
        while True:
            cursor.execute(
                "SELECT counter, version FROM user_counter WHERE user_id = %s", 
                (1,)
            )
            counter, version = cursor.fetchone()
            
            new_counter = counter + 1
            new_version = version + 1
            
            cursor.execute(
                """UPDATE user_counter 
                   SET counter = %s, version = %s 
                   WHERE user_id = %s AND version = %s""",
                (new_counter, new_version, 1, version)
            )
            conn.commit()
            
            if cursor.rowcount > 0:
                break

    cursor.close()
    conn.close()
    print(f"Потік {thread_id} завершив роботу")

def do(f):
    start = time.time()
    threads = []
    for i in range(10):
        thread = threading.Thread(target=f, args=(i,))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    end = time.time()  
    print(f"Час виконання {str(f.__name__)}: {end - start:.2f} секунд")

if __name__ == "__main__":
    do(LostUpdate)
    do(InPlaceUpdate)
    do(RowLevelLocking)
    do(OptimisticConcurrencyControl)
