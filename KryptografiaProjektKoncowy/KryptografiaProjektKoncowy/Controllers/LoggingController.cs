using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using KryptografiaProjektKoncowy.Models;
using Microsoft.AspNetCore.Mvc;

namespace KryptografiaProjektKoncowy.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LoggingController : ControllerBase
    {
        [HttpGet]
        public ActionResult<int> Logging()
        {
            User newUser = new User();
            Data.UsersList.Add(newUser);

            return newUser.Id;
        }
    }
}