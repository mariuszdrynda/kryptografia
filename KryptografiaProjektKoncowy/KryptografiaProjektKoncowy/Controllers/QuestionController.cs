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
    public class QuestionController : ControllerBase
    {
        [HttpGet("{question}")]
        public ActionResult<bool> AskQuestion(string question)
        {
            if (Data.AskedQuestion == null)
            {
                Data.AskedQuestion = question;
                return true;
            }
                return false;         
        }

        [HttpGet]
        public ActionResult<string> QuestionValue()
        {
            return Data.AskedQuestion;
        }
    }
}
