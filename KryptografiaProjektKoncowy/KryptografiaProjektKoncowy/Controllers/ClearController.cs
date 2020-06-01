using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using KryptografiaProjektKoncowy.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace KryptografiaProjektKoncowy.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClearController : ControllerBase
    {
        [HttpGet]
        public ActionResult<bool> Clear()
        {
            Data.UsersList.Clear();
            Data.AskedQuestion = null;
            Models.User.UsersNumber = 0;
            return true;
        }
    }
}