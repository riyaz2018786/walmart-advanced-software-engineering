import sqlite3
import pandas as pd

def main():
    db_path = 'shipping_database.db'
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    df0 = pd.read_csv('spreadsheet_0.csv')
    df1 = pd.read_csv('spreadsheet_1.csv')
    df2 = pd.read_csv('spreadsheet_2.csv')

    for _, row in df0.iterrows():
        cursor.execute(
            "INSERT OR IGNORE INTO shipping_data (shipping_id, product, origin, destination, quantity) VALUES (?, ?, ?, ?, ?)",
            (row.get('shipping_id'), row.get('product'), row.get('origin'), row.get('destination'), row.get('quantity'))
        )

    merged_df = pd.merge(df1, df2, on='shipping_id', how='inner')
    
    aggregated_df = merged_df.groupby(['shipping_id', 'product', 'origin', 'destination']).agg({'quantity': 'sum'}).reset_index()

    for _, row in aggregated_df.iterrows():
        cursor.execute(
            "INSERT INTO shipping_data (shipping_id, product, origin, destination, quantity) VALUES (?, ?, ?, ?, ?)",
            (row['shipping_id'], row['product'], row['origin'], row['destination'], row['quantity'])
        )

    conn.commit()
    conn.close()

if __name__ == '__main__':
    main()
