# The OAuth 2.0 Authorization
## Introduction
In the traditional Client-Server authentication model, the client requests an access-restricted resource (Protected Resource) on the server by authenticating with the server using the
resource owner's credentials. In order to provide third-party applications access to restricted resources, the owner shares its credentials with the third party. However, this creates 
several problems and limitations:
- Third-party applications are required to store the resource owner's credentials for future use, typically a password in clear-text.
- Servers are required to support password authentication, despite the security weaknesses inherent in passwords.
- Third-party applications gain overly broad access to the resource owner's protected resources, leaving resource owners without any ability to restrict duration or access to a limited
  subset of resources.
- Resource owners cannot revoke access to an individual third party without revoking access to all third parties, and must do so by changing the third party's password.
- Compromise of any third-party application results in compromise of the end-user's password and all of the data protected by that password.

`OAuth` (Open Authentication) is a delegation protocol, built on IETF standards and licenses from the Open Web Foundation
## Concepts
OAuth defines 4 roles:
- `Resource Owner`: An entity capable of granting access to a protected resource. When the resource owner is a person, it's referred to as an end-user.
- `Resource Server`:
- `Client`:
- `Authorization Server` (Auth Server):

## Reference
- [Dick Hardt, _The OAuth 2.0 Authorization Framework_](https://datatracker.ietf.org/doc/html/rfc6749#section-1.1)