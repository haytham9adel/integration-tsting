import requests
import json

# Adapt this for your server
BASE_URL = 'http://0.0.0.0:8080'
ADMIN_USER = 'admin'
ADMIN_PWD = 'password'
auth = (ADMIN_USER, ADMIN_PWD)


# create killbill API tenant
url = BASE_URL + '/1.0/kb/tenants'

params = {
'apiKey': 'cartwheel',
'apiSecret': 'secret',
        }

headers = {
    'X-Killbill-CreatedBy': ADMIN_USER,
     'content-type': 'application/json'
}

# Send the request
r = requests.post(
    url,
    auth=auth,
    json=params,
    headers=headers,
    )

print r.status_code
