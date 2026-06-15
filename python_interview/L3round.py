data = {
    "sessions": [
        {
            "session_id": "S1",
            "user": {
                "id": 42,
                "profile": {
                    "name": "Alex",
                    "emails": [
                        {
                            "value": "alex.primary@email.com",
                            "verified": True
                        },
                        {
                            "value": "alex.secondary@email.com",
                            "verified": False
                        }
                    ]
                }
            },
            "transactions": [
                {
                    "txn_id": "T1",
                    "status": "SUCCESS",
                    "items": [
                        {
                            "name": "Book",
                            "price": 15.0,
                            "quantity": 2
                        },
                        {
                            "name": "Pen",
                            "price": 5.0,
                            "quantity": 1
                        }
                    ]
                },
                {
                    "txn_id": "T2",
                    "status": "FAILED",
                    "items": [
                        {
                            "name": "Laptop",
                            "price": 1000.0,
                            "quantity": 1
                        }
                    ]
                }
            ]
        },
        {
            "session_id": "S2",
            "user": {
                "id": 99,
                "profile": {
                    "name": "Sam",
                    "emails": [
                        {
                            "value": "sam@email.com",
                            "verified": False
                        }
                    ]
                }
            },
            "transactions": [
                {
                    "txn_id": "T3",
                    "status": "SUCCESS",
                    "items": [
                        {
                            "name": "Phone",
                            "price": 500.0,
                            "quantity": 1
                        }
                    ]
                },
                {
                    "txn_id": "T4",
                    "status": "SUCCESS",
                    "items": []
                }
            ]
        }
    ]
}

result = []
for i in data["sessions"]:
    user_id = i["user"]["id"]
    primary_email = None
    for e in i["user"]["profile"]["emails"]:
        if e["verified"]:
            primary_email = e["value"]
            break
    total_spent = 0
    total_items = 0
    failed_txns = 0
    for t in i["transactions"]:
        if t["status"] == "SUCCESS":
            for item in t["items"]:
                quantity = item.get("quantity") or 0
                price = item.get("price") or 0

                total_items += quantity
                total_spent += price * quantity
        else:
            failed_txns += 1
    user_data = {
        "user_id": user_id,
        "primary_email": primary_email,
        "total_spent": total_spent,
        "total_items": total_items,
        "failed_txns": failed_txns
    }

    result.append(user_data)

print(result)