db.createUser(
    {
        user: "payments-user",
        pwd: "payments-user-123",
        roles: [
            {
                role: "readWrite",
                db: "payments"
            }
        ]

    }
);
