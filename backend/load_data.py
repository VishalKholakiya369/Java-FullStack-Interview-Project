import csv
import psycopg2

# Database config
DB_HOST = "localhost"
DB_PORT = "5432"
DB_NAME = "ecommerce_db"
DB_USER = "postgres"
DB_PASSWORD = "admin123"

# Connect to PostgreSQL
conn = psycopg2.connect(
    host=DB_HOST,
    port=DB_PORT,
    dbname=DB_NAME,
    user=DB_USER,
    password=DB_PASSWORD
)
cur = conn.cursor()


def load_csv_to_table(file_path, insert_query, row_mapper):
    with open(file_path, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            cur.execute(insert_query, row_mapper(row))
    conn.commit()


# ------------ Load Distribution Centers ------------
load_csv_to_table(
    "distribution_centers.csv",
    """
    INSERT INTO distribution_center (id, name, location)
    VALUES (%s, %s, %s)
    """,
    lambda row: (row['id'], row['name'], row['location'])
)

# ------------ Load Products ------------
load_csv_to_table(
    "products.csv",
    """
    INSERT INTO product (id, name, description, price, stock_quantity, distribution_center_id)
    VALUES (%s, %s, %s, %s, %s, %s)
    """,
    lambda row: (row['id'], row['name'], row['description'], row['price'], row['stock_quantity'], row['distribution_center_id'])
)

# ------------ Load Inventory Items ------------
load_csv_to_table(
    "inventory_items.csv",
    """
    INSERT INTO inventory_item (id, product_id, quantity_available)
    VALUES (%s, %s, %s)
    """,
    lambda row: (row['id'], row['product_id'], row['quantity_available'])
)

# ------------ Load Users ------------
load_csv_to_table(
    "users.csv",
    """
    INSERT INTO users (id, name, email, password)
    VALUES (%s, %s, %s, %s)
    """,
    lambda row: (row['id'], row['name'], row['email'], row['password'])
)

# ------------ Load Orders ------------
load_csv_to_table(
    "orders.csv",
    """
    INSERT INTO orders (id, user_id, order_date)
    VALUES (%s, %s, %s)
    """,
    lambda row: (row['id'], row['user_id'], row['order_date'])
)

# ------------ Load Order Items ------------
load_csv_to_table(
    "order_items.csv",
    """
    INSERT INTO order_item (id, order_id, product_id, quantity)
    VALUES (%s, %s, %s, %s)
    """,
    lambda row: (row['id'], row['order_id'], row['product_id'], row['quantity'])
)

# Done
cur.close()
conn.close()
print("✅ All CSV data successfully loaded into PostgreSQL.")
